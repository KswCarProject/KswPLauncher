package com.wits.ksw.launcher.bmw_id8_ui;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.SettingsSystemUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GSID8LauncherConstants {
    private static final String GS_ID8_CARD_SEQ = "GS_ID8_card_seq";
    private static final String GS_ID8_LEFT_BAR_ICON3 = "GS_ID8_left_bar_icon_3";
    private static final String GS_ID8_LEFT_BAR_ICON4 = "GS_ID8_left_bar_icon_4";
    private static final String GS_ID8_LEFT_BAR_ICON5 = "GS_ID8_left_bar_icon_5";
    private static final String TAG = "GSID8LauncherConstants";
    private static Context context;
    public static String leftIcon3;
    public static String leftIcon4;
    public static String leftIcon5;
    public static List<String> nameList = new ArrayList();

    public static void init(Context appContext) {
        context = appContext;
        loadLeftBarIcon();
        loadSeq();
    }

    public static void loadLeftBarIcon() {
        String cacheIcon3 = Settings.System.getString(context.getContentResolver(), GS_ID8_LEFT_BAR_ICON3);
        if (TextUtils.isEmpty(cacheIcon3)) {
            leftIcon3 = "MUSIC";
        } else {
            leftIcon3 = cacheIcon3;
        }
        String cacheIcon4 = Settings.System.getString(context.getContentResolver(), GS_ID8_LEFT_BAR_ICON4);
        if (TextUtils.isEmpty(cacheIcon4)) {
            leftIcon4 = "PHONE";
        } else {
            leftIcon4 = cacheIcon4;
        }
        String cacheIcon5 = Settings.System.getString(context.getContentResolver(), GS_ID8_LEFT_BAR_ICON5);
        if (TextUtils.isEmpty(cacheIcon5)) {
            leftIcon5 = "CAR INFO";
        } else {
            leftIcon5 = cacheIcon5;
        }
    }

    public static void loadSeq() {
        nameList.clear();
        String cardSeq = Settings.System.getString(context.getContentResolver(), GS_ID8_CARD_SEQ);
        if (cardSeq == null) {
            initGsSeq();
            return;
        }
        String[] split = cardSeq.split(";");
        if (split.length == 0) {
            initGsSeq();
        } else {
            nameList.addAll(Arrays.asList(split));
        }
    }

    public static void saveSystemCardSeq() {
        List<String> list = nameList;
        if (list != null && list.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String name : nameList) {
                stringBuilder.append(name);
                stringBuilder.append(";");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            String cardSeq = stringBuilder.toString();
            Log.w(TAG, "cache card seq : " + SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), GS_ID8_CARD_SEQ, cardSeq));
            Log.w(TAG, "cache card seq : " + cardSeq);
        }
    }

    private static void initGsSeq() {
        nameList.add("DASHBOARD");
        nameList.add("MUSIC");
        nameList.add("PHONE");
        nameList.add("CAR INFO");
        nameList.add("SETTING");
        nameList.add("VIDEO");
        nameList.add("WEATHER");
        nameList.add("MODUS");
        nameList.add("ADD WIDGET");
    }

    public static boolean changeLeftIcon(int viewId, String name) {
        if (TextUtils.isEmpty(name) || leftIcon3.equals(name) || leftIcon4.equals(name) || leftIcon5.equals(name)) {
            return false;
        }
        switch (viewId) {
            case R.id.ll_left_3 /*2131297272*/:
                leftIcon3 = name;
                SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), GS_ID8_LEFT_BAR_ICON3, name);
                return true;
            case R.id.ll_left_4 /*2131297273*/:
                leftIcon4 = name;
                SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), GS_ID8_LEFT_BAR_ICON4, name);
                return true;
            case R.id.ll_left_5 /*2131297274*/:
                leftIcon5 = name;
                SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), GS_ID8_LEFT_BAR_ICON5, name);
                return true;
            default:
                return false;
        }
    }

    public static void removeCard(String tag) {
        nameList.remove(tag);
    }

    public static void addTrdApp(String pkg, String cls) {
        String pkgInfo = "3rd:" + pkg + "," + cls;
        if (!nameList.contains(pkgInfo)) {
            int settingPosition = nameList.size() - 1;
            if (pkg.equals("com.txznet.weather") && !nameList.contains("WEATHER")) {
                nameList.add(settingPosition, "WEATHER");
            } else if (!pkg.equals("com.txznet.weather") || !nameList.contains("WEATHER")) {
                nameList.add(settingPosition, pkgInfo);
            } else {
                return;
            }
        }
        saveSystemCardSeq();
    }
}
