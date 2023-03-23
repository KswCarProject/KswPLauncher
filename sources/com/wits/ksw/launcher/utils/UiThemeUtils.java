package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

public class UiThemeUtils {
    public static final String ALS_ID6 = "ALS_ID6_UI";
    public static final String ALS_ID7_UI = "ALS_ID7_UI";
    public static final String Audi_MMI_4G = "Audi_MMI_4G";
    public static final String Audi_mib3 = "Audi_mib3";
    public static final String Audi_mib3_FY = "Audi_mib3_FY";
    public static final String Audi_mib3_FY_V2 = "Audi_mib3_FY_V2";
    public static final String Audi_mib3_ty = "Audi_mib3_ty";
    public static final String BMW_EVO_ID5 = "BMW_EVO_ID5";
    public static final String BMW_EVO_ID6 = "BMW_EVO_ID6";
    public static final String BMW_EVO_ID6_CUSP = "BMW_EVO_ID6_CUSP";
    public static final String BMW_EVO_ID6_GS = "BMW_EVO_ID6_GS";
    public static final String BMW_EVO_ID7 = "BMW_EVO_ID7";
    public static final String BMW_EVO_ID7_HiCar = "BMW_EVO_ID7_HiCar";
    public static final String BMW_EVO_ID7_V2 = "BMW_EVO_ID7_V2";
    public static final String BMW_ID8_UI = "BMW_ID8_UI";
    public static final String BMW_NBT = "BMW_NBT_UI";
    public static final String Benz_GS = "Benz_GS";
    public static final String Benz_MBUX = "Benz_MBUX";
    public static final String Benz_MBUX_2021 = "Benz_MBUX_2021";
    public static final String Benz_MBUX_2021_KSW = "Benz_MBUX_2021_KSW";
    public static final String Benz_MBUX_2021_KSW_V2 = "Benz_MBUX_2021_KSW_V2";
    public static final String Benz_NTG5 = "Benz_NTG5";
    public static final String Benz_NTG6 = "Benz_NTG6";
    public static final String Benz_NTG6_FY = "Benz_NTG6_FY";
    public static final String Common_UI_GS = "Common_UI_GS";
    public static final String Common_UI_GS_UG = "Common_UI_GS_UG";
    public static final String Common_UI_GS_UG_1024 = "Common_UI_GS_UG_1024";
    public static final String ID7_ALS = "PEMP_ID7_UI";
    public static final String ID7_ALS_V2 = "PEMP_ID7_UI_V2";
    public static final String LAND_ROVER = "LAND_ROVER";
    public static final String LEXUS_LS_UI = "LEXUS_LS_UI";
    public static final String LEXUS_LS_UI_V2 = "LEXUS_LS_UI_V2";
    public static final String LEXUS_UI = "LEXUS_UI";
    public static final String ROMEO_UI = "Alfa_Romeo";
    public static final String UI_GS_ID8 = "UI_GS_ID8";
    public static final String UI_KSW_ID7 = "UI_KSW_ID7";
    public static final String UI_KSW_MBUX_1024 = "UI_KSW_MBUX_1024";
    public static final String UI_NTG6_FY_V2 = "UI_NTG6_FY_V2";
    public static final String UI_mib3_V2 = "UI_mib3_V2";

    public static boolean isId7AndDefaultUi(Context context) {
        return !isBMW_EVO_ID6(context) && !isBMW_EVO_ID5(context) && !isBenz_NTG6(context) && !isBenz_MBUX(context) && !isCommon_UI_GS(context) && !isCommon_UI_GS_UG(context) && !isAudi_MMI_4G(context) && !isBenz_GS(context) && !isBMW_EVO_ID6_GS(context) && !isBenz_NTG5(context) && !isALS_ID6(context) && !isBMW_EVO_ID7_HiCar(context) && !isLEXUS_UI(context) && !isBMW_NBT(context) && !isROMEO_UI(context) && !isID7_ALS(context) && !isID7_ALS_V2(context) && !isLAND_ROVER(context);
    }

    public static boolean isLAND_ROVER(Context context) {
        return TextUtils.equals(getUiName(context), LAND_ROVER);
    }

    public static boolean isID7_ALS(Context context) {
        return TextUtils.equals(getUiName(context), ID7_ALS);
    }

    public static boolean isID7_ALS_V2(Context context) {
        return TextUtils.equals(getUiName(context), ID7_ALS_V2);
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

    public static boolean isUI_KSW_ID7(Context context) {
        return TextUtils.equals(getUiName(context), UI_KSW_ID7);
    }

    public static boolean isBMW_EVO_ID6(Context context) {
        return TextUtils.equals(getUiName(context), BMW_EVO_ID6);
    }

    public static boolean isBMW_EVO_ID6_CUSP(Context context) {
        return TextUtils.equals(getUiName(context), BMW_EVO_ID6_CUSP);
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

    public static boolean isAudi_mib3(Context context) {
        return TextUtils.equals(getUiName(context), Audi_mib3);
    }

    public static boolean isUI_mib3_V2(Context context) {
        return TextUtils.equals(getUiName(context), UI_mib3_V2);
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

    public static boolean isUI_KSW_MBUX_1024(Context context) {
        return TextUtils.equals(getUiName(context), UI_KSW_MBUX_1024);
    }

    public static boolean isLEXUS_LS_UI(Context context) {
        return TextUtils.equals(getUiName(context), LEXUS_LS_UI);
    }

    public static boolean isLEXUS_LS_UI_V2(Context context) {
        return TextUtils.equals(getUiName(context), LEXUS_LS_UI_V2);
    }

    public static boolean isBenz_MBUX_2021(Context context) {
        return TextUtils.equals(getUiName(context), Benz_MBUX_2021);
    }

    public static boolean isBenz_MBUX_2021_KSW(Context context) {
        return TextUtils.equals(getUiName(context), Benz_MBUX_2021_KSW);
    }

    public static boolean isBenz_MBUX_2021_KSW_V2(Context context) {
        return TextUtils.equals(getUiName(context), Benz_MBUX_2021_KSW_V2);
    }

    public static boolean isBenz_NTG6_FY(Context context) {
        return TextUtils.equals(getUiName(context), Benz_NTG6_FY);
    }

    public static boolean isUI_NTG6_FY_V2(Context context) {
        return TextUtils.equals(getUiName(context), UI_NTG6_FY_V2);
    }

    public static boolean isALS_ID7_UI(Context context) {
        return TextUtils.equals(getUiName(context), ALS_ID7_UI);
    }

    public static boolean isBMW_EVO_ID7_V2(Context context) {
        return TextUtils.equals(getUiName(context), BMW_EVO_ID7_V2);
    }

    public static boolean isAudi_mib3_FY(Context context) {
        return TextUtils.equals(getUiName(context), Audi_mib3_FY);
    }

    public static boolean isAudi_mib3_FY_V2(Context context) {
        return TextUtils.equals(getUiName(context), Audi_mib3_FY_V2);
    }

    public static boolean isAudi_mib3_ty(Context context) {
        return TextUtils.equals(getUiName(context), Audi_mib3_ty);
    }

    public static boolean isBMW_ID8_UI(Context context) {
        return TextUtils.equals(getUiName(context), BMW_ID8_UI);
    }

    public static boolean isUI_GS_ID8(Context context) {
        return TextUtils.equals(getUiName(context), UI_GS_ID8);
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static int getCarManufacturer(Context context) {
        if (isBMW_EVO_ID5(context) || isBMW_EVO_ID6(context)) {
            return 1;
        }
        if ((isBMW_EVO_ID6_GS(context) && isBMW_EVO_ID7(context)) || isCommon_UI_GS(context) || isCommon_UI_GS_UG(context) || isBMW_EVO_ID6_CUSP(context) || isBMW_NBT(context)) {
            return 1;
        }
        if (isBenz_NTG5(context) || isBenz_GS(context) || isBenz_NTG6(context) || isBenz_MBUX(context) || isBenz_MBUX_2021(context) || isBenz_NTG6_FY(context) || isUI_NTG6_FY_V2(context) || isBenz_MBUX_2021_KSW(context) || isBenz_MBUX_2021_KSW_V2(context)) {
            return 2;
        }
        if (isAudi_MMI_4G(context) || isAudi_mib3(context) || isUI_mib3_V2(context) || isAudi_mib3_FY(context) || isAudi_mib3_FY_V2(context) || isAudi_mib3_ty(context)) {
            return 3;
        }
        if (isLEXUS_UI(context) || isLEXUS_LS_UI(context) || isLEXUS_LS_UI_V2(context)) {
            return 4;
        }
        return 0;
    }

    public static String getUiName(Context context) {
        return Settings.System.getString(context.getContentResolver(), "UiName");
    }

    public static String getThemeName(Context context) {
        return Settings.System.getString(context.getContentResolver(), "UiName");
    }
}
