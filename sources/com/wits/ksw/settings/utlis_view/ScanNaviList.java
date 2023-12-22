package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.settings.id7.bean.MapBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes10.dex */
public class ScanNaviList {
    private boolean isScanNavi;
    private List<MapBean> mapList;
    private OnMapListScanListener mapListScanListener;
    private String naviDefual;
    private List<MapBean> tempList;

    /* loaded from: classes10.dex */
    public interface OnMapListScanListener {
        void onScanFinish(List<MapBean> mapList);
    }

    private ScanNaviList() {
        this.naviDefual = "";
        this.isScanNavi = false;
    }

    /* loaded from: classes10.dex */
    private static class SingletonInstance {
        private static final ScanNaviList INSTANCE = new ScanNaviList();

        private SingletonInstance() {
        }
    }

    public static ScanNaviList getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public List<MapBean> getMapList() {
        List<MapBean> list = this.mapList;
        if (list == null) {
            return new ArrayList();
        }
        return list;
    }

    public synchronized void scanList(final List<String> naviList, String naviDefual, final Context context) {
        this.tempList = new ArrayList();
        this.naviDefual = naviDefual;
        new Thread(new Runnable() { // from class: com.wits.ksw.settings.utlis_view.ScanNaviList.1
            @Override // java.lang.Runnable
            public void run() {
                if (!ScanNaviList.this.isScanNavi) {
                    ScanNaviList.this.isScanNavi = true;
                    Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
                    intent.addCategory("android.intent.category.LAUNCHER");
                    List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(intent, 0);
                    Collections.sort(apps, new Comparator<ResolveInfo>() { // from class: com.wits.ksw.settings.utlis_view.ScanNaviList.1.1
                        @Override // java.util.Comparator
                        public int compare(ResolveInfo a, ResolveInfo b) {
                            return String.CASE_INSENSITIVE_ORDER.compare(a.loadLabel(context.getPackageManager()).toString(), b.loadLabel(context.getPackageManager()).toString());
                        }
                    });
                    Log.i("naviSCAN", "run: apps size = " + apps.size());
                    Log.i("naviSCAN", "run: naviList size = " + naviList.size());
                    for (String paca : naviList) {
                        ScanNaviList scanNaviList = ScanNaviList.this;
                        scanNaviList.isAvilible(context, paca, scanNaviList.tempList, apps);
                    }
                    ScanNaviList scanNaviList2 = ScanNaviList.this;
                    scanNaviList2.mapList = scanNaviList2.tempList;
                    Log.i("naviSCAN", "run: map size " + ScanNaviList.this.tempList.size());
                    if (ScanNaviList.this.mapListScanListener != null) {
                        ScanNaviList.this.mapListScanListener.onScanFinish(ScanNaviList.this.tempList);
                    }
                }
                ScanNaviList.this.isScanNavi = false;
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isAvilible(final Context context, String packageName, final List<MapBean> mbList, List<ResolveInfo> apps) {
        for (int i = 0; i < apps.size(); i++) {
            try {
                ResolveInfo info = apps.get(i);
                String packName = info.activityInfo.packageName;
                if (!TextUtils.isEmpty(packageName) && packageName.equals(packName)) {
                    MapBean mapBean = new MapBean();
                    mapBean.setPackageName(packName);
                    mapBean.setName(info.activityInfo.loadLabel(context.getPackageManager()).toString());
                    mapBean.setMapicon(info.activityInfo.loadIcon(context.getPackageManager()));
                    mapBean.setCheck(packName.equals(this.naviDefual));
                    if (!mbList.isEmpty() && mbList.contains(mapBean)) {
                        return;
                    }
                    Log.d("naviSCAN", "===appPackage===" + packName);
                    mbList.add(mapBean);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void setMapListScanListener(OnMapListScanListener mapListScanListener) {
        this.mapListScanListener = mapListScanListener;
    }
}
