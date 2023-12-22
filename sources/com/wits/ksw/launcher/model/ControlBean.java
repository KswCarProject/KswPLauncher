package com.wits.ksw.launcher.model;

import android.content.Context;
import android.database.ContentObserver;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes9.dex */
public class ControlBean {
    private static final String TAG = "KswApplication." + ControlBean.class.getSimpleName();
    public ObservableBoolean controlPanelClose = new ObservableBoolean();
    public ObservableBoolean benzControlPanelState = new ObservableBoolean();
    public final ObservableBoolean chassis = new ObservableBoolean();
    public final ObservableBoolean sport = new ObservableBoolean();
    public final ObservableBoolean rdarAssistance = new ObservableBoolean();
    public final ObservableBoolean leftBrightnessAdjus = new ObservableBoolean();
    public final ObservableBoolean rightBrightnessAdjus = new ObservableBoolean();
    public final ObservableInt brightness = new ObservableInt();
    public final ObservableBoolean passairbar = new ObservableBoolean();

    public ControlBean(Context context) {
        updataControl();
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.BENZPANE), false, new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.model.ControlBean.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                ControlBean.this.updataControl();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updataControl() {
        try {
            int benzpane = PowerManagerApp.getSettingsInt(KeyConfig.BENZPANE);
            boolean z = true;
            this.controlPanelClose.set(benzpane == 0);
            String str = TAG;
            StringBuilder append = new StringBuilder().append("benzControlPanel: ");
            if (benzpane != 0) {
                z = false;
            }
            Log.i(str, append.append(z).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
