package com.wits.ksw.launcher.dabebase;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class AppInfoDao_Impl implements AppInfoDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfAppList;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public AppInfoDao_Impl(RoomDatabase __db2) {
        this.__db = __db2;
        this.__insertionAdapterOfAppList = new EntityInsertionAdapter<AppList>(__db2) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `appList_table`(`uid`,`className`) VALUES (nullif(?, 0),?)";
            }

            public void bind(SupportSQLiteStatement stmt, AppList value) {
                stmt.bindLong(1, (long) value.getUid());
                if (value.className == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.className);
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db2) {
            public String createQuery() {
                return "delete from appList_table";
            }
        };
    }

    public void insert(List<AppList> appList) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAppList.insert(appList);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteAll() {
        SupportSQLiteStatement _stmt = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(_stmt);
        }
    }

    public List<AppList> queryAll() {
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("select * from appList_table ", 0);
        Cursor _cursor = this.__db.query(_statement);
        try {
            int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
            int _cursorIndexOfClassName = _cursor.getColumnIndexOrThrow("className");
            List<AppList> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                AppList _item = new AppList();
                _item.setUid(_cursor.getInt(_cursorIndexOfUid));
                _item.className = _cursor.getString(_cursorIndexOfClassName);
                _result.add(_item);
            }
            return _result;
        } finally {
            _cursor.close();
            _statement.release();
        }
    }
}
