package com.wits.ksw.launcher.view.benzmbux2021;

import android.content.Context;
import android.provider.Settings;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;

public class BenzMbux2021Configs {
    public static final String BG_INDEX = "BG_INDEX";
    public static final int[] BG_ONE = {R.drawable.mbux2_bg_main_1_1, R.drawable.mbux2_bg_main_1_2, R.drawable.mbux2_bg_main_1_3, R.drawable.mbux2_bg_main_1_4, R.drawable.mbux2_bg_main_1_5, R.drawable.mbux2_bg_main_1_6, R.drawable.mbux2_bg_main_1_7};
    public static final int[] BG_OTHER = {R.drawable.mbux2_bg_main_2_1, R.drawable.mbux2_bg_main_2_2, R.drawable.mbux2_bg_main_2_3, R.drawable.mbux2_bg_main_2_4, R.drawable.mbux2_bg_main_2_5, R.drawable.mbux2_bg_main_2_6, R.drawable.mbux2_bg_main_2_7};
    public static final String BG_SAVE = "BG_SAVE";
    public static final String STYLE_INDEX = "STYLE_INDEX";
    public static int bg_sel = 1;
    public static int style_sel = 1;

    public static void saveStyleData(Context context) {
        LOGE.D("___________________saveStyleData()________________");
        Settings.System.putString(context.getContentResolver(), "STYLE_INDEX", style_sel + "");
        Settings.System.putString(context.getContentResolver(), "BG_INDEX", bg_sel + "");
    }
}
