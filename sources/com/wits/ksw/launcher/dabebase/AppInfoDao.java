package com.wits.ksw.launcher.dabebase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface AppInfoDao {
    @Query("delete from appList_table")
    void deleteAll();

    @Insert
    void insert(List<AppList> list);

    @Query("select * from appList_table ")
    List<AppList> queryAll();
}
