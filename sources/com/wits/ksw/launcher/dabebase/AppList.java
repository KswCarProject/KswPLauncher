package com.wits.ksw.launcher.dabebase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "appList_table")
public final class AppList {
    @NonNull
    public String className;
    @PrimaryKey(autoGenerate = true)
    private int uid;

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid2) {
        this.uid = uid2;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className2) {
        this.className = className2;
    }
}
