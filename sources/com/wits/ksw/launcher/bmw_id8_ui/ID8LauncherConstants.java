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

public class ID8LauncherConstants {
    private static final String ID8_CARD_SEQ = "ID8_card_seq";
    private static final String ID8_LEFT_BAR_ICON2 = "ID8_left_bar_icon_2";
    private static final String ID8_LEFT_BAR_ICON3 = "ID8_left_bar_icon_3";
    private static final String ID8_LEFT_BAR_ICON4 = "ID8_left_bar_icon_4";
    public static final String ID8_SKIN = "ID8_skin";
    public static final String ID8_SKIN_EFFICIENT = "blue";
    public static final String ID8_SKIN_PERSONAL = "yellow";
    public static final String ID8_SKIN_SPORT = "red";
    private static final String TAG = "ID8LauncherConstants";
    private static Context context;
    public static String leftIcon2;
    public static String leftIcon3;
    public static String leftIcon4;
    public static List<String> nameList = new ArrayList();

    public static void init(Context appContext) {
        context = appContext;
        loadLeftBarIcon();
        loadSeq();
    }

    public static String loadCurrentSkin() {
        String skinName = Settings.System.getString(context.getContentResolver(), ID8_SKIN);
        if (TextUtils.isEmpty(skinName)) {
            return ID8_SKIN_PERSONAL;
        }
        return skinName;
    }

    public static void loadLeftBarIcon() {
        String cacheIcon2 = Settings.System.getString(context.getContentResolver(), ID8_LEFT_BAR_ICON2);
        if (TextUtils.isEmpty(cacheIcon2)) {
            leftIcon2 = "MUSIC";
        } else {
            leftIcon2 = cacheIcon2;
        }
        String cacheIcon3 = Settings.System.getString(context.getContentResolver(), ID8_LEFT_BAR_ICON3);
        if (TextUtils.isEmpty(cacheIcon3)) {
            leftIcon3 = "PHONE";
        } else {
            leftIcon3 = cacheIcon3;
        }
        String cacheIcon4 = Settings.System.getString(context.getContentResolver(), ID8_LEFT_BAR_ICON4);
        if (TextUtils.isEmpty(cacheIcon4)) {
            leftIcon4 = "NAVIGATE";
        } else {
            leftIcon4 = cacheIcon4;
        }
    }

    public static void loadSeq() {
        nameList.clear();
        String cardSeq = Settings.System.getString(context.getContentResolver(), ID8_CARD_SEQ);
        if (cardSeq == null) {
            initSeq();
            return;
        }
        String[] split = cardSeq.split(";");
        if (split.length == 0) {
            initSeq();
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
            Log.w(TAG, "cache card seq : " + SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), ID8_CARD_SEQ, cardSeq));
            Log.w(TAG, "cache card seq : " + cardSeq);
        }
    }

    private static void initSeq() {
        nameList.add("NAVIGATE");
        nameList.add("WEATHER");
        nameList.add("MUSIC");
        nameList.add("CAR INFO");
        nameList.add("MODUS");
        nameList.add("PHONE");
        nameList.add("DASHBOARD");
        nameList.add("SETTING");
        nameList.add("VIDEO");
        nameList.add("ADD WIDGET");
    }

    public static boolean changeLeftIcon(int viewId, String name) {
        if (TextUtils.isEmpty(name) || leftIcon2.equals(name) || leftIcon3.equals(name) || leftIcon4.equals(name)) {
            return false;
        }
        switch (viewId) {
            case R.id.ll_left_2 /*2131297271*/:
                leftIcon2 = name;
                SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), ID8_LEFT_BAR_ICON2, name);
                return true;
            case R.id.ll_left_3 /*2131297272*/:
                leftIcon3 = name;
                SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), ID8_LEFT_BAR_ICON3, name);
                return true;
            case R.id.ll_left_4 /*2131297273*/:
                leftIcon4 = name;
                SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), ID8_LEFT_BAR_ICON4, name);
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

    private static boolean saveSkin(String skinName) {
        return SettingsSystemUtil.putString(KswApplication.appContext.getContentResolver(), ID8_SKIN, skinName);
    }

    public static boolean checkModusHasChanged(String constantsName) {
        if (constantsName.equals(loadCurrentSkin())) {
            return false;
        }
        return saveSkin(constantsName);
    }
}
