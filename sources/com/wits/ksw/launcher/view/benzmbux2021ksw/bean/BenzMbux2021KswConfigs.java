package com.wits.ksw.launcher.view.benzmbux2021ksw.bean;

import android.content.Context;
import android.provider.Settings;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;

/* loaded from: classes11.dex */
public class BenzMbux2021KswConfigs {
    public static final String BG_INDEX = "BG_INDEX";
    public static final String BG_SAVE = "BG_SAVE";
    public static final String STYLE_INDEX = "STYLE_INDEX";
    public static int bg_sel = 1;
    public static int style_sel = 1;
    public static final int[] BG_ONE = {C0899R.C0900drawable.mbux2_bg_main_1_1, C0899R.C0900drawable.mbux2_bg_main_1_2, C0899R.C0900drawable.mbux2_bg_main_1_3, C0899R.C0900drawable.mbux2_bg_main_1_4, C0899R.C0900drawable.mbux2_bg_main_1_5, C0899R.C0900drawable.mbux2_bg_main_1_6, C0899R.C0900drawable.mbux2_bg_main_1_7};
    public static final int[] BG_OTHER = {C0899R.C0900drawable.ksw_mbux2_bg_main_2_1, C0899R.C0900drawable.ksw_mbux2_bg_main_2_2, C0899R.C0900drawable.ksw_mbux2_bg_main_2_3, C0899R.C0900drawable.ksw_mbux2_bg_main_2_4, C0899R.C0900drawable.ksw_mbux2_bg_main_2_5, C0899R.C0900drawable.ksw_mbux2_bg_main_2_6, C0899R.C0900drawable.ksw_mbux2_bg_main_2_7};

    public static void saveStyleData(Context context) {
        LOGE.m44D("___________________saveStyleData()________________");
        Settings.System.putString(context.getContentResolver(), "STYLE_INDEX", style_sel + "");
        Settings.System.putString(context.getContentResolver(), "BG_INDEX", bg_sel + "");
    }
}
