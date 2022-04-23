package com.wits.ksw.launcher.view.lexusls;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class LexusLsConfig {
    public static String AppPkgs = "";
    public static int Height = 0;
    public static final String IS_FIRST = "IS_FIRST";
    public static final String LEXUS_LS_SAVE_PKG = "LEXUS_LS_SAVE_PKG";
    public static final int MORE = 99999;
    public static String MainAtys = "";
    public static final String PKG_DEFINED_MENU_LEXUSLS = "lexus.ls.defined.pkgname";
    public static final String[] PKG_MENU_STRS = {"lexus.ls.defined.pkgname.0", "lexus.ls.defined.pkgname.1", "lexus.ls.defined.pkgname.2", "lexus.ls.defined.pkgname.3", "lexus.ls.defined.pkgname.4", "lexus.ls.defined.pkgname.5", "lexus.ls.defined.pkgname.6", "lexus.ls.defined.pkgname.7", "lexus.ls.defined.pkgname.8", "lexus.ls.defined.pkgname.9", "lexus.ls.defined.pkgname.10", "lexus.ls.defined.pkgname.11", "lexus.ls.defined.pkgname.12"};
    public static int Width;
    public static boolean isUpdateApp = false;
    public static String mAddAppAction = "wits.installapp.recevieraction";
    public static ArrayList<LexusLsAppSelBean> mAppList = new ArrayList<>();
    public static String mRemoveAppAction = "wits.removeapp.recevieraction";
    public static String mReplaceAppAction = "wits.repalceapp.recevieraction";

    public static String[] getMenuNameList(Context context) {
        return new String[]{context.getResources().getString(R.string.ksw_id7_navi).trim(), context.getResources().getString(R.string.ksw_id7_music).trim(), context.getResources().getString(R.string.id6_phone).trim(), context.getResources().getString(R.string.gs_application).trim(), context.getResources().getString(R.string.video).trim(), context.getResources().getString(R.string.ksw_id7_car).trim(), context.getResources().getString(R.string.ksw_id7_setting).trim(), context.getResources().getString(R.string.lexus_ac).trim(), context.getResources().getString(R.string.function_text8).trim(), context.getResources().getString(R.string.ksw_id7_dashboard_lable).trim(), context.getResources().getString(R.string.id6_shouj_hulian).trim(), context.getResources().getString(R.string.id6_filemanager).trim(), context.getResources().getString(R.string.add_app).trim()};
    }

    public static Drawable[] getMenuDrawList(Context context) {
        return new Drawable[]{context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_gps), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_music), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_bt), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_app), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_video), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_car), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_settings), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_air), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_dvr), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_dashboard), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_phonelink), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_file), context.getResources().getDrawable(R.drawable.lexus_ls_main_icon_cut_add)};
    }

    public static boolean isMenu(String pkg) {
        int i = 0;
        while (true) {
            String[] strArr = PKG_MENU_STRS;
            if (i >= strArr.length) {
                return false;
            }
            if (pkg.equals(strArr[i])) {
                return true;
            }
            i++;
        }
    }

    public static LexusLsAppSelBean findBeanByPkg(String pkg, ArrayList<LexusLsAppSelBean> beans) {
        for (int i = 0; i < beans.size(); i++) {
            LexusLsAppSelBean bean = beans.get(i);
            if (pkg.equals(bean.getAppPkg())) {
                return bean;
            }
        }
        return null;
    }

    public static ArrayList<LexusLsAppSelBean> getMenuAppList(Context context) {
        String[] STR_NAME_MENU = getMenuNameList(context);
        Drawable[] DRAWABLE_NAME_MENU = getMenuDrawList(context);
        ArrayList<LexusLsAppSelBean> list = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            LexusLsAppSelBean bean = new LexusLsAppSelBean();
            bean.setAppName(STR_NAME_MENU[i]);
            bean.setAppMainAty("lexus.ls.defined.pkgname." + i);
            bean.setAppIcon(DRAWABLE_NAME_MENU[i]);
            bean.setAppPkg("lexus.ls.defined.pkgname." + i);
            bean.setItemPos(i);
            bean.setDeletable(false);
            list.add(bean);
        }
        return list;
    }

    public static List<LexusLsAppSelBean> listrem(Context ctx, List<LexusLsAppSelBean> listA, List<LexusLsAppSelBean> sourceList) {
        if (sourceList == null || sourceList.size() < 0) {
            return null;
        }
        HashSet hs1 = new HashSet(listA);
        hs1.removeAll(new HashSet(sourceList));
        List<LexusLsAppSelBean> listC = new ArrayList<>();
        listC.addAll(hs1);
        return listC;
    }

    public static List<LexusLsAppSelBean> getRemoveListResult(Context cxt, List<LexusLsAppSelBean> all, List<LexusLsAppSelBean> sourceList) {
        if (all != null && sourceList != null && all.size() > 0 && sourceList.size() > 0) {
            ArrayList<LexusLsAppSelBean> currentList = new ArrayList<>();
            currentList.addAll(all);
            Iterator<LexusLsAppSelBean> it = currentList.iterator();
            while (it.hasNext()) {
                LexusLsAppSelBean parentBean = it.next();
                for (LexusLsAppSelBean childBean : sourceList) {
                    if (TextUtils.equals(childBean.getAppPkg(), parentBean.getAppPkg())) {
                        all.remove(parentBean);
                    }
                }
            }
        }
        LOGE.E("getRemoveResult() apps size : " + all.size());
        return all;
    }

    public static ArrayList<String> getAppsPackage(List<LexusLsAppSelBean> all) {
        ArrayList<String> pkgs = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            pkgs.add(all.get(i).getAppPkg());
        }
        return pkgs;
    }

    public static void saveDataOrder(Context context) {
        int appCount = mAppList.size();
        AppPkgs = "";
        MainAtys = "";
        for (int i = 0; i < appCount; i++) {
            LexusLsAppSelBean item = mAppList.get(i);
            AppPkgs += item.getAppPkg();
            AppPkgs += ";";
            MainAtys += item.getAppMainAty();
            MainAtys += ";";
        }
        Settings.System.putString(context.getContentResolver(), LEXUS_LS_SAVE_PKG, AppPkgs);
        Settings.System.putString(context.getContentResolver(), IS_FIRST, "false");
        Log.e("ContentValues", "saveDataOrder =" + appCount);
    }
}
