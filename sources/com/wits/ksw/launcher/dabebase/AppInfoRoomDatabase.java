package com.wits.ksw.launcher.dabebase;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {AppList.class}, version = 1)
public abstract class AppInfoRoomDatabase extends RoomDatabase {
    private static volatile AppInfoRoomDatabase INSTANCE;

    public abstract AppInfoDao getAppInfoDao();

    public static AppInfoRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppInfoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppInfoRoomDatabase.class, "appList").build();
                }
            }
        }
        return INSTANCE;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public InvalidationTracker createInvalidationTracker() {
        return null;
    }

    public void clearAllTables() {
    }
}
