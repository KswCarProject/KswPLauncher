package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

public class UiThemeUtils {
    public static final String ALS_ID6 = "ALS_ID6_UI";
    public static final String Audi_MMI_4G = "Audi_MMI_4G";
    public static final String BMW_EVO_ID5 = "BMW_EVO_ID5";
    public static final String BMW_EVO_ID6 = "BMW_EVO_ID6";
    public static final String BMW_EVO_ID6_GS = "BMW_EVO_ID6_GS";
    public static final String BMW_EVO_ID7 = "BMW_EVO_ID7";
    public static final String BMW_EVO_ID7_HiCar = "BMW_EVO_ID7_HiCar";
    public static final String BMW_NBT = "BMW_NBT_UI";
    public static final String Benz_GS = "Benz_GS";
    public static final String Benz_MBUX = "Benz_MBUX";
    public static final String Benz_NTG5 = "Benz_NTG5";
    public static final String Benz_NTG6 = "Benz_NTG6";
    public static final String Common_UI_GS = "Common_UI_GS";
    public static final String Common_UI_GS_UG = "Common_UI_GS_UG";
    public static final String Common_UI_GS_UG_1024 = "Common_UI_GS_UG_1024";
    public static final String LEXUS_UI = "LEXUS_UI";
    public static final String ROMEO_UI = "Alfa_Romeo";

    public static boolean isId7AndDefaultUi(Context context) {
        return !isBMW_EVO_ID6(context) && !isBMW_EVO_ID5(context) && !isBenz_NTG6(context) && !isBenz_MBUX(context) && !isCommon_UI_GS(context) && !isCommon_UI_GS_UG(context) && !isAudi_MMI_4G(context) && !isBenz_GS(context) && !isBMW_EVO_ID6_GS(context) && !isBenz_NTG5(context) && !isALS_ID6(context) && !isBMW_EVO_ID7_HiCar(context) && !isLEXUS_UI(context) && !isBMW_NBT(context) && !isROMEO_UI(context);
    }

    public static boolean isBenz_NTG6(Context context) {
        return TextUtils.equals(getUiName(context), Benz_NTG6);
    }

    public static boolean isBenz_NTG5(Context context) {
        return TextUtils.equals(getUiName(context), Benz_NTG5);
    }

    public static boolean isBMW_EVO_ID7(Context context) {
        return TextUtils.equals(getUiName(context), BMW_EVO_ID7);
    }

    public static boolean isBMW_EVO_ID6(Context context) {
        return TextUtils.equals(getUiName(context), BMW_EVO_ID6);
    }

    public static boolean isBMW_EVO_ID5(Context context) {
        return TextUtils.equals(getUiName(context), BMW_EVO_ID5);
    }

    public static boolean isCommon_UI_GS(Context context) {
        return TextUtils.equals(getUiName(context), Common_UI_GS);
    }

    public static boolean isCommon_UI_GS_UG(Context context) {
        return TextUtils.equals(getUiName(context), Common_UI_GS_UG);
    }

    public static boolean isBenz_MBUX(Context context) {
        return TextUtils.equals(getUiName(context), Benz_MBUX);
    }

    public static boolean isAudi_MMI_4G(Context context) {
        return TextUtils.equals(getUiName(context), Audi_MMI_4G);
    }

    public static boolean isBenz_GS(Context context) {
        return TextUtils.equals(getUiName(context), Benz_GS);
    }

    public static boolean isBMW_EVO_ID6_GS(Context context) {
        return TextUtils.equals(getUiName(context), BMW_EVO_ID6_GS);
    }

    public static boolean isALS_ID6(Context context) {
        return TextUtils.equals(getUiName(context), ALS_ID6);
    }

    public static boolean isBMW_EVO_ID7_HiCar(Context context) {
        return TextUtils.equals(getUiName(context), BMW_EVO_ID7_HiCar);
    }

    public static boolean isLEXUS_UI(Context context) {
        return TextUtils.equals(getUiName(context), LEXUS_UI);
    }

    public static boolean isBMW_NBT(Context context) {
        return TextUtils.equals(getUiName(context), BMW_NBT);
    }

    public static boolean isROMEO_UI(Context context) {
        return TextUtils.equals(getUiName(context), ROMEO_UI);
    }

    public static boolean isCommon_UI_GS_UG_1024(Context context) {
        return TextUtils.equals(getUiName(context), Common_UI_GS_UG_1024);
    }

    public static int getCarManufacturer(Context context) {
        if (isBMW_EVO_ID5(context) || isBMW_EVO_ID6(context)) {
            return 1;
        }
        if ((isBMW_EVO_ID6_GS(context) && isBMW_EVO_ID7(context)) || isCommon_UI_GS(context) || isCommon_UI_GS_UG(context)) {
            return 1;
        }
        if (isBenz_NTG5(context) || isBenz_GS(context) || isBenz_NTG6(context) || isBenz_MBUX(context)) {
            return 2;
        }
        if (isAudi_MMI_4G(context)) {
            return 3;
        }
        return 0;
    }

    public static String getUiName(Context context) {
        return Settings.System.getString(context.getContentResolver(), "UiName");
    }
}
