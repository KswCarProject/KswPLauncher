package com.wits.ksw.launcher.view.benzntg6fy;

import android.content.Context;
import android.provider.Settings;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;

public class BenzNtg6FyConfigs {
    public static final String BG_INDEX = "BG_INDEX_BENZ_NTG6_FY";
    public static final int[] BG_ONE = {R.drawable.fy_mbux_bg_main_1, R.drawable.fy_mbux_bg_main_2, R.drawable.fy_mbux_bg_main_3, R.drawable.fy_mbux_bg_main_4, R.drawable.fy_mbux_bg_main_5, R.drawable.fy_mbux_bg_main_6, R.drawable.fy_mbux_bg_main_7, R.drawable.fy_mbux_bg_main_8};
    public static final String BG_SAVE = "BG_SAVE_BENZ_NTG6_FY";
    public static final String STYLE_INDEX = "STYLE_INDEX_BENZ_NTG6_FY";
    public static int bg_sel = 1;
    public static int style_sel = 1;

    public static void saveStyleData(Context context) {
        LOGE.D("___________________BenzNtg6FyConfigs saveStyleData()________________");
        Settings.System.putString(context.getContentResolver(), STYLE_INDEX, style_sel + "");
        Settings.System.putString(context.getContentResolver(), BG_INDEX, bg_sel + "");
    }
}
