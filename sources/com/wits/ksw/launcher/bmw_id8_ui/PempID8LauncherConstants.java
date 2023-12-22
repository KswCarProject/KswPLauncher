package com.wits.ksw.launcher.bmw_id8_ui;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.utils.SettingsSystemUtil;
import com.wits.ksw.launcher.view.benzmbux2021new.util.BenzUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class PempID8LauncherConstants {
    private static final String PEMP_ID8_CARD_SEQ = "PEMP_ID8_card_seq";
    private static final String PEMP_ID8_LEFT_BAR_ICON2 = "PEMP_ID8_left_bar_icon_2";
    private static final String PEMP_ID8_LEFT_BAR_ICON3 = "PEMP_ID8_left_bar_icon_3";
    private static final String PEMP_ID8_LEFT_BAR_ICON4 = "PEMP_ID8_left_bar_icon_4";
    private static final String PEMP_ID8_LEFT_BAR_ICON5 = "PEMP_ID8_left_bar_icon_5";
    private static final String TAG = "PEMPID8LauncherConstants";
    private static Context context;
    public static String leftIcon2;
    public static String leftIcon3;
    public static String leftIcon4;
    public static String leftIcon5;
    public static List<String> nameList = new ArrayList();

    public static void init(Context appContext) {
        Log.e(TAG, "init: ");
        context = appContext;
        loadLeftBarIcon();
        loadSeq();
    }

    public static void loadLeftBarIcon() {
        Log.e(TAG, "loadLeftBarIcon: ");
        String cacheIcon2 = Settings.System.getString(context.getContentResolver(), PEMP_ID8_LEFT_BAR_ICON2);
        if (TextUtils.isEmpty(cacheIcon2)) {
            leftIcon2 = "SETTING";
        } else {
            leftIcon2 = cacheIcon2;
        }
        String cacheIcon3 = Settings.System.getString(context.getContentResolver(), PEMP_ID8_LEFT_BAR_ICON3);
        if (TextUtils.isEmpty(cacheIcon3)) {
            leftIcon3 = "MUSIC";
        } else {
            leftIcon3 = cacheIcon3;
        }
        String cacheIcon4 = Settings.System.getString(context.getContentResolver(), PEMP_ID8_LEFT_BAR_ICON4);
        if (TextUtils.isEmpty(cacheIcon4)) {
            leftIcon4 = "PHONE";
        } else {
            leftIcon4 = cacheIcon4;
        }
        String cacheIcon5 = Settings.System.getString(context.getContentResolver(), PEMP_ID8_LEFT_BAR_ICON5);
        if (TextUtils.isEmpty(cacheIcon5)) {
            leftIcon5 = "NAVIGATE";
        } else {
            leftIcon5 = cacheIcon5;
        }
        Log.e(TAG, "loadLeftBarIcon: leftIcon3=" + leftIcon3 + " leftIcon4=" + leftIcon4 + " leftIcon5" + leftIcon5);
    }

    public static void loadSeq() {
        Log.e(TAG, "loadSeq: ");
        nameList.clear();
        String cardSeq = Settings.System.getString(context.getContentResolver(), PEMP_ID8_CARD_SEQ);
        if (cardSeq == null) {
            initPempSeq();
            return;
        }
        String[] split = cardSeq.split(";");
        if (split.length == 0) {
            initPempSeq();
            return;
        }
        nameList.addAll(Arrays.asList(split));
        ID8LauncherConstants.IteratorNameList(nameList);
    }

    public static void saveSystemCardSeq() {
        Log.e(TAG, "saveSystemCardSeq: ");
        List<String> list = nameList;
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : nameList) {
            stringBuilder.append(name);
            stringBuilder.append(";");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String cardSeq = stringBuilder.toString();
        boolean b = SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), PEMP_ID8_CARD_SEQ, cardSeq);
        Log.w(TAG, "cache card seq : " + b);
        Log.w(TAG, "cache card seq : " + cardSeq);
    }

    private static void initPempSeq() {
        Log.d(TAG, "initPempSeq: ");
        nameList.add("NAVIGATE");
        nameList.add("WEATHER");
        nameList.add("MUSIC");
        nameList.add("DASHBOARD");
        nameList.add("MODUS");
        nameList.add("PHONE");
        nameList.add("CAR INFO");
        nameList.add("SETTING");
        nameList.add("VIDEO");
        nameList.add("ADD WIDGET");
    }

    public static boolean changeLeftIcon(int viewId, String name) {
        Log.d(TAG, "changeLeftIcon: ");
        if (TextUtils.isEmpty(name) || leftIcon2.equals(name) || leftIcon3.equals(name) || leftIcon4.equals(name) || leftIcon5.equals(name)) {
            return false;
        }
        switch (viewId) {
            case C0899R.C0901id.ll_left_2 /* 2131297300 */:
                leftIcon2 = name;
                SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), PEMP_ID8_LEFT_BAR_ICON2, name);
                return true;
            case C0899R.C0901id.ll_left_3 /* 2131297301 */:
                leftIcon3 = name;
                SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), PEMP_ID8_LEFT_BAR_ICON3, name);
                return true;
            case C0899R.C0901id.ll_left_4 /* 2131297302 */:
                leftIcon4 = name;
                SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), PEMP_ID8_LEFT_BAR_ICON4, name);
                return true;
            case C0899R.C0901id.ll_left_5 /* 2131297303 */:
                leftIcon5 = name;
                SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), PEMP_ID8_LEFT_BAR_ICON5, name);
                return true;
            default:
                return false;
        }
    }

    public static void removeCard(String tag) {
        nameList.remove(tag);
    }

    public static void addTrdApp(String pkg, String cls) {
        Log.d(TAG, "addTrdApp: ");
        String pkgInfo = "3rd:" + pkg + "," + cls;
        if (!nameList.contains(pkgInfo)) {
            int settingPosition = nameList.size() - 1;
            if (pkg.equals(BenzUtils.WEATHER_PKG) && !nameList.contains("WEATHER")) {
                nameList.add(settingPosition, "WEATHER");
            } else if (pkg.equals(BenzUtils.WEATHER_PKG) && nameList.contains("WEATHER")) {
                return;
            } else {
                nameList.add(settingPosition, pkgInfo);
            }
        }
        saveSystemCardSeq();
    }
}
