package com.wits.ksw.launcher.utils;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import com.wits.ksw.C0899R;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes11.dex */
public class NaviInfo {
    public static final String AUTONAVI_STANDARD_BROADCAST_SEND = "AUTONAVI_STANDARD_BROADCAST_SEND";
    public static final String CAMERA_DIST = "CAMERA_DIST";
    public static final String CAMERA_INDEX = "CAMERA_INDEX";
    public static final String CAMERA_SPEED = "CAMERA_SPEED";
    public static final String CAMERA_TYPE = "CAMERA_TYPE";
    public static final String CUR_POINT_NUM = "CUR_POINT_NUM";
    public static final String CUR_ROAD_NAME = "CUR_ROAD_NAME";
    public static final String CUR_SPEED = "CUR_SPEED";
    public static final String ICON = "ICON";
    public static final String NEXT_ROAD_NAME = "NEXT_ROAD_NAME";
    public static final String ROAD_TYPE = "ROAD_TYPE";
    public static final String ROUND_ABOUT_NUM = "ROUNG_ABOUT_NUM";
    public static final String ROUTE_ALL_DIS = "ROUTE_ALL_DIS";
    public static final String ROUTE_ALL_TIME = "ROUTE_ALL_TIME";
    public static final String ROUTE_REMAIN_DIS = "ROUTE_REMAIN_DIS";
    public static final String ROUTE_REMAIN_TIME = "ROUTE_REMAIN_TIME";
    public static final String SAPA_DIST = "SAPA_DIST";
    public static final String SAPA_NAME = "SAPA_NAME";
    public static final String SAPA_NUM = "SAPA_NUM";
    public static final String SAPA_TYPE = "SAPA_TYPE";
    public static final String SEG_REMAIN_DIS = "SEG_REMAIN_DIS";
    public static final String SEG_REMAIN_TIME = "SEG_REMAIN_TIME";
    private static final String TAG = "NaviInfo";
    public static final String TRAFFIC_LIGHT_NUM = "TRAFFIC_LIGHT_NUM";
    public static final String TYPE = "TYPE";
    public static int[] iconId = {C0899R.C0900drawable.amap_info_01, C0899R.C0900drawable.amap_info_02, C0899R.C0900drawable.amap_info_03, C0899R.C0900drawable.amap_info_04, C0899R.C0900drawable.amap_info_05, C0899R.C0900drawable.amap_info_06, C0899R.C0900drawable.amap_info_07, C0899R.C0900drawable.amap_info_08, C0899R.C0900drawable.amap_info_09, C0899R.C0900drawable.amap_info_10, C0899R.C0900drawable.amap_info_11, C0899R.C0900drawable.amap_info_12, C0899R.C0900drawable.amap_info_13, C0899R.C0900drawable.amap_info_14, C0899R.C0900drawable.amap_info_15, C0899R.C0900drawable.amap_info_16, C0899R.C0900drawable.amap_info_17, C0899R.C0900drawable.amap_info_18, C0899R.C0900drawable.amap_info_19, C0899R.C0900drawable.amap_info_20, C0899R.C0900drawable.amap_info_21, C0899R.C0900drawable.amap_info_22, C0899R.C0900drawable.amap_info_23, C0899R.C0900drawable.amap_info_24, C0899R.C0900drawable.amap_info_25, C0899R.C0900drawable.amap_info_26, C0899R.C0900drawable.amap_info_27, C0899R.C0900drawable.amap_info_28};
    private Boolean showGuideEnable = false;
    public final ObservableInt showGuideView = new ObservableInt(-1);
    public final ObservableField<Integer> type = new ObservableField<>();
    public final ObservableField<String> next_road_name = new ObservableField<>();
    public final ObservableField<Integer> camera_type = new ObservableField<>();
    public final ObservableField<Integer> camera_speed = new ObservableField<>();
    public final ObservableField<Integer> icon = new ObservableField<>();
    public final ObservableField<String> route_remain_dis = new ObservableField<>();
    public final ObservableField<String> route_remain_time = new ObservableField<>();
    public final ObservableField<String> seg_remain_dis = new ObservableField<>();

    public boolean isGuideEnable() {
        return this.showGuideEnable.booleanValue();
    }

    public void setShowGuideEnable(Boolean showGuideEnable) {
        this.showGuideEnable = showGuideEnable;
    }

    public static String formatDistance(int m) {
        if (m >= 1000) {
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            return decimalFormat.format(m / 1000.0d) + " KM";
        }
        return m + " M";
    }

    public static String formatTime(int time) {
        if (time % 60 > 0.5d) {
            time += 60;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(0, 0, 0, 0, 0, time);
        return simpleDateFormat.format(date);
    }

    public static int formatIcon(int iconIndex) {
        int index = iconIndex - 1;
        if (index >= 0) {
            int[] iArr = iconId;
            if (index < iArr.length) {
                return iArr[index];
            }
            return 0;
        }
        return 0;
    }
}
