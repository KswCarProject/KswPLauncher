package com.wits.ksw.launcher.dabebase;

import android.arch.persistence.p000db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/* loaded from: classes10.dex */
public abstract class AppInfoRoomDatabase extends RoomDatabase {
    private static volatile AppInfoRoomDatabase INSTANCE;

    public abstract AppInfoDao getAppInfoDao();

    public static AppInfoRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppInfoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = (AppInfoRoomDatabase) Room.databaseBuilder(context, AppInfoRoomDatabase.class, "appList").build();
                }
            }
        }
        return INSTANCE;
    }

    @Override // android.arch.persistence.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override // android.arch.persistence.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override // android.arch.persistence.room.RoomDatabase
    public void clearAllTables() {
    }
}
