package com.wits.ksw.launcher.dabebase;

import android.arch.persistence.p000db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public class AppInfoDao_Impl implements AppInfoDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfAppList;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public AppInfoDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfAppList = new EntityInsertionAdapter<AppList>(__db) { // from class: com.wits.ksw.launcher.dabebase.AppInfoDao_Impl.1
            @Override // android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `appList_table`(`uid`,`className`) VALUES (nullif(?, 0),?)";
            }

            @Override // android.arch.persistence.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, AppList value) {
                stmt.bindLong(1, value.getUid());
                if (value.className == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.className);
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.wits.ksw.launcher.dabebase.AppInfoDao_Impl.2
            @Override // android.arch.persistence.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from appList_table";
            }
        };
    }

    @Override // com.wits.ksw.launcher.dabebase.AppInfoDao
    public void insert(List<AppList> appList) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAppList.insert((Iterable) appList);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wits.ksw.launcher.dabebase.AppInfoDao
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

    @Override // com.wits.ksw.launcher.dabebase.AppInfoDao
    public List<AppList> queryAll() {
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("select * from appList_table ", 0);
        Cursor _cursor = this.__db.query(_statement);
        try {
            int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
            int _cursorIndexOfClassName = _cursor.getColumnIndexOrThrow("className");
            List<AppList> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                AppList _item = new AppList();
                int _tmpUid = _cursor.getInt(_cursorIndexOfUid);
                _item.setUid(_tmpUid);
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
