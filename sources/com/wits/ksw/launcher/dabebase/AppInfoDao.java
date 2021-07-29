package com.wits.ksw.launcher.dabebase;

import java.util.List;

public interface AppInfoDao {
    void deleteAll();

    void insert(List<AppList> list);

    List<AppList> queryAll();
}
