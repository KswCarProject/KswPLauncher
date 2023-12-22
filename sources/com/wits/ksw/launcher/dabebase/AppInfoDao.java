package com.wits.ksw.launcher.dabebase;

import java.util.List;

/* loaded from: classes10.dex */
public interface AppInfoDao {
    void deleteAll();

    void insert(List<AppList> appList);

    List<AppList> queryAll();
}
