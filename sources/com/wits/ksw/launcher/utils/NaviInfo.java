package com.wits.ksw.launcher.utils;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import com.wits.ksw.R;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static int[] iconId = {R.drawable.amap_info_01, R.drawable.amap_info_02, R.drawable.amap_info_03, R.drawable.amap_info_04, R.drawable.amap_info_05, R.drawable.amap_info_06, R.drawable.amap_info_07, R.drawable.amap_info_08, R.drawable.amap_info_09, R.drawable.amap_info_10, R.drawable.amap_info_11, R.drawable.amap_info_12, R.drawable.amap_info_13, R.drawable.amap_info_14, R.drawable.amap_info_15, R.drawable.amap_info_16, R.drawable.amap_info_17, R.drawable.amap_info_18, R.drawable.amap_info_19, R.drawable.amap_info_20, R.drawable.amap_info_21, R.drawable.amap_info_22, R.drawable.amap_info_23, R.drawable.amap_info_24, R.drawable.amap_info_25, R.drawable.amap_info_26, R.drawable.amap_info_27, R.drawable.amap_info_28};
    public final ObservableField<Integer> camera_speed = new ObservableField<>();
    public final ObservableField<Integer> camera_type = new ObservableField<>();
    public final ObservableField<Integer> icon = new ObservableField<>();
    public final ObservableField<String> next_road_name = new ObservableField<>();
    public final ObservableField<String> route_remain_dis = new ObservableField<>();
    public final ObservableField<String> route_remain_time = new ObservableField<>();
    public final ObservableField<String> seg_remain_dis = new ObservableField<>();
    private Boolean showGuideEnable = false;
    public final ObservableInt showGuideView = new ObservableInt(-1);
    public final ObservableField<Integer> type = new ObservableField<>();

    public boolean isGuideEnable() {
        return this.showGuideEnable.booleanValue();
    }

    public void setShowGuideEnable(Boolean showGuideEnable2) {
        this.showGuideEnable = showGuideEnable2;
    }

    public static String formatDistance(int m) {
        if (m < 1000) {
            return m + " M";
        }
        return new DecimalFormat("0.0").format(((double) m) / 1000.0d) + " KM";
    }

    public static String formatTime(int time) {
        if (((double) (time % 60)) > 0.5d) {
            time += 60;
        }
        return new SimpleDateFormat("HH:mm").format(new Date(0, 0, 0, 0, 0, time));
    }

    public static int formatIcon(int iconIndex) {
        int index = iconIndex - 1;
        if (index < 0) {
            return 0;
        }
        int[] iArr = iconId;
        if (index < iArr.length) {
            return iArr[index];
        }
        return 0;
    }
}
