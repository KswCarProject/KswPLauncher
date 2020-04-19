package com.wits.ksw.launcher.utils;

import android.content.SharedPreferences;
import com.wits.ksw.KswApplication;

public class SharedPreUtils {
    private static final String UI_ID_TYLE = "UI_ID_TYLE";
    private static volatile SharedPreUtils singleton;
    private SharedPreferences preferences = KswApplication.appContext.getSharedPreferences("kswcar", 4);

    private SharedPreUtils() {
    }

    public static SharedPreUtils getInstance() {
        if (singleton == null) {
            synchronized (SharedPreUtils.class) {
                if (singleton == null) {
                    singleton = new SharedPreUtils();
                }
            }
        }
        return singleton;
    }

    public int getUiIDType() {
        return this.preferences.getInt(UI_ID_TYLE, 7);
    }

    public void setUiIDType(int idType) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putInt(UI_ID_TYLE, idType);
        editor.apply();
    }
}
