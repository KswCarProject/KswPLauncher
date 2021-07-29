package com.wits.ksw.launcher.dabebase;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public abstract class AppInfoRoomDatabase extends RoomDatabase {
    private static volatile AppInfoRoomDatabase INSTANCE;

    public abstract AppInfoDao getAppInfoDao();

    public static AppInfoRoomDatabase getDatabase(Context context) {
        Class<AppInfoRoomDatabase> cls = AppInfoRoomDatabase.class;
        if (INSTANCE == null) {
            synchronized (cls) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, cls, "appList").build();
                }
            }
        }
        return INSTANCE;
    }

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return null;
    }

    public void clearAllTables() {
    }
}
