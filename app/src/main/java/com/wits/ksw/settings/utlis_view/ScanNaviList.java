package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import com.wits.ksw.settings.id7.bean.MapBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScanNaviList {
    /* access modifiers changed from: private */
    public boolean isScanNavi;
    /* access modifiers changed from: private */
    public List<MapBean> mapList;
    /* access modifiers changed from: private */
    public OnMapListScanListener mapListScanListener;
    private String naviDefual;
    /* access modifiers changed from: private */
    public List<MapBean> tempList;

    public interface OnMapListScanListener {
        void onScanFinish(List<MapBean> list);
    }

    private ScanNaviList() {
        this.naviDefual = "";
        this.isScanNavi = false;
    }

    private static class SingletonInstance {
        /* access modifiers changed from: private */
        public static final ScanNaviList INSTANCE = new ScanNaviList();

        private SingletonInstance() {
        }
    }

    public static ScanNaviList getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public List<MapBean> getMapList() {
        if (this.mapList == null) {
            return new ArrayList();
        }
        return this.mapList;
    }

    public synchronized void scanList(final List<String> naviList, String naviDefual2, final Context context) {
        this.tempList = new ArrayList();
        this.naviDefual = naviDefual2;
        new Thread(new Runnable() {
            public void run() {
                if (!ScanNaviList.this.isScanNavi) {
                    boolean unused = ScanNaviList.this.isScanNavi = true;
                    for (String paca : naviList) {
                        ScanNaviList.this.isAvilible(context, paca, ScanNaviList.this.tempList);
                    }
                    List unused2 = ScanNaviList.this.mapList = ScanNaviList.this.tempList;
                    if (ScanNaviList.this.mapListScanListener != null) {
                        ScanNaviList.this.mapListScanListener.onScanFinish(ScanNaviList.this.tempList);
                    }
                }
                boolean unused3 = ScanNaviList.this.isScanNavi = false;
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void isAvilible(Context context, String packageName, List<MapBean> mbList) {
        ScanNaviList scanNaviList = this;
        List<MapBean> list = mbList;
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        int i = 0;
        List apps = context.getPackageManager().queryIntentActivities(intent, 0);
        final Context context2 = context;
        Collections.sort(apps, new Comparator<ResolveInfo>() {
            public int compare(ResolveInfo a, ResolveInfo b) {
                return String.CASE_INSENSITIVE_ORDER.compare(a.loadLabel(context2.getPackageManager()).toString(), b.loadLabel(context2.getPackageManager()).toString());
            }
        });
        while (i < apps.size()) {
            ResolveInfo info = apps.get(i);
            String packName = info.activityInfo.packageName;
            CharSequence appName = info.activityInfo.loadLabel(context.getPackageManager());
            Drawable iconbm = info.activityInfo.loadIcon(context.getPackageManager());
            if (packageName.equals(packName)) {
                MapBean mapBean = new MapBean();
                mapBean.setPackageName(packName);
                mapBean.setName(appName.toString());
                mapBean.setMapicon(iconbm);
                if (packName.equals(scanNaviList.naviDefual)) {
                    mapBean.setCheck(true);
                }
                for (MapBean map : mbList) {
                    if (map.getName().equals(mapBean.getName()) && map.getPackageName().equals(mapBean.getPackageName())) {
                    }
                }
                if (mbList.isEmpty() || !list.contains(mapBean)) {
                    Log.d("naviSCAN", "===appPackage===" + packName);
                    list.add(mapBean);
                }
            }
            i++;
            scanNaviList = this;
        }
        String str = packageName;
    }

    public void setMapListScanListener(OnMapListScanListener mapListScanListener2) {
        this.mapListScanListener = mapListScanListener2;
    }
}
