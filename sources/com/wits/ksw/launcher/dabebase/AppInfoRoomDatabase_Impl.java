package com.wits.ksw.launcher.dabebase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomMasterTable;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.util.TableInfo;
import java.util.HashMap;
import java.util.HashSet;

public class AppInfoRoomDatabase_Impl extends AppInfoRoomDatabase {
    private volatile AppInfoDao _appInfoDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `appList_table` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `className` TEXT NOT NULL)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"2828c787948669ef5ff291539e502ded\")");
            }

            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `appList_table`");
            }

            /* access modifiers changed from: protected */
            public void onCreate(SupportSQLiteDatabase _db) {
                if (AppInfoRoomDatabase_Impl.this.mCallbacks != null) {
                    int _size = AppInfoRoomDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((RoomDatabase.Callback) AppInfoRoomDatabase_Impl.this.mCallbacks.get(_i)).onCreate(_db);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase _db) {
                SupportSQLiteDatabase unused = AppInfoRoomDatabase_Impl.this.mDatabase = _db;
                AppInfoRoomDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (AppInfoRoomDatabase_Impl.this.mCallbacks != null) {
                    int _size = AppInfoRoomDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((RoomDatabase.Callback) AppInfoRoomDatabase_Impl.this.mCallbacks.get(_i)).onOpen(_db);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void validateMigration(SupportSQLiteDatabase _db) {
                HashMap<String, TableInfo.Column> _columnsAppListTable = new HashMap<>(2);
                _columnsAppListTable.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
                _columnsAppListTable.put("className", new TableInfo.Column("className", "TEXT", true, 0));
                TableInfo _infoAppListTable = new TableInfo("appList_table", _columnsAppListTable, new HashSet<>(0), new HashSet<>(0));
                TableInfo _existingAppListTable = TableInfo.read(_db, "appList_table");
                if (!_infoAppListTable.equals(_existingAppListTable)) {
                    throw new IllegalStateException("Migration didn't properly handle appList_table(com.wits.ksw.launcher.dabebase.AppList).\n Expected:\n" + _infoAppListTable + "\n Found:\n" + _existingAppListTable);
                }
            }
        }, "2828c787948669ef5ff291539e502ded", "d4edf6b2018e016beab5666f80ef6681")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, "appList_table");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            _db.execSQL("DELETE FROM `appList_table`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            _db.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!_db.inTransaction()) {
                _db.execSQL("VACUUM");
            }
        }
    }

    public AppInfoDao getAppInfoDao() {
        AppInfoDao appInfoDao;
        if (this._appInfoDao != null) {
            return this._appInfoDao;
        }
        synchronized (this) {
            if (this._appInfoDao == null) {
                this._appInfoDao = new AppInfoDao_Impl(this);
            }
            appInfoDao = this._appInfoDao;
        }
        return appInfoDao;
    }
}
