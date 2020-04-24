package android.arch.persistence.db.framework;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Pair;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

class FrameworkSQLiteDatabase implements SupportSQLiteDatabase {
    private static final String[] CONFLICT_VALUES = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final SQLiteDatabase mDelegate;

    FrameworkSQLiteDatabase(SQLiteDatabase delegate) {
        this.mDelegate = delegate;
    }

    public SupportSQLiteStatement compileStatement(String sql) {
        return new FrameworkSQLiteStatement(this.mDelegate.compileStatement(sql));
    }

    public void beginTransaction() {
        this.mDelegate.beginTransaction();
    }

    public void beginTransactionNonExclusive() {
        this.mDelegate.beginTransactionNonExclusive();
    }

    public void beginTransactionWithListener(SQLiteTransactionListener transactionListener) {
        this.mDelegate.beginTransactionWithListener(transactionListener);
    }

    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener transactionListener) {
        this.mDelegate.beginTransactionWithListenerNonExclusive(transactionListener);
    }

    public void endTransaction() {
        this.mDelegate.endTransaction();
    }

    public void setTransactionSuccessful() {
        this.mDelegate.setTransactionSuccessful();
    }

    public boolean inTransaction() {
        return this.mDelegate.inTransaction();
    }

    public boolean isDbLockedByCurrentThread() {
        return this.mDelegate.isDbLockedByCurrentThread();
    }

    public boolean yieldIfContendedSafely() {
        return this.mDelegate.yieldIfContendedSafely();
    }

    public boolean yieldIfContendedSafely(long sleepAfterYieldDelay) {
        return this.mDelegate.yieldIfContendedSafely(sleepAfterYieldDelay);
    }

    public int getVersion() {
        return this.mDelegate.getVersion();
    }

    public void setVersion(int version) {
        this.mDelegate.setVersion(version);
    }

    public long getMaximumSize() {
        return this.mDelegate.getMaximumSize();
    }

    public long setMaximumSize(long numBytes) {
        return this.mDelegate.setMaximumSize(numBytes);
    }

    public long getPageSize() {
        return this.mDelegate.getPageSize();
    }

    public void setPageSize(long numBytes) {
        this.mDelegate.setPageSize(numBytes);
    }

    public Cursor query(String query) {
        return query((SupportSQLiteQuery) new SimpleSQLiteQuery(query));
    }

    public Cursor query(String query, Object[] bindArgs) {
        return query((SupportSQLiteQuery) new SimpleSQLiteQuery(query, bindArgs));
    }

    public Cursor query(final SupportSQLiteQuery supportQuery) {
        return this.mDelegate.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() {
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
                supportQuery.bindTo(new FrameworkSQLiteProgram(query));
                return new SQLiteCursor(masterQuery, editTable, query);
            }
        }, supportQuery.getSql(), EMPTY_STRING_ARRAY, (String) null);
    }

    @RequiresApi(api = 16)
    public Cursor query(final SupportSQLiteQuery supportQuery, CancellationSignal cancellationSignal) {
        return this.mDelegate.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() {
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
                supportQuery.bindTo(new FrameworkSQLiteProgram(query));
                return new SQLiteCursor(masterQuery, editTable, query);
            }
        }, supportQuery.getSql(), EMPTY_STRING_ARRAY, (String) null, cancellationSignal);
    }

    public long insert(String table, int conflictAlgorithm, ContentValues values) throws SQLException {
        return this.mDelegate.insertWithOnConflict(table, (String) null, values, conflictAlgorithm);
    }

    public int delete(String table, String whereClause, Object[] whereArgs) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(table);
        if (TextUtils.isEmpty(whereClause)) {
            str = "";
        } else {
            str = " WHERE " + whereClause;
        }
        sb.append(str);
        SupportSQLiteStatement statement = compileStatement(sb.toString());
        SimpleSQLiteQuery.bind(statement, whereArgs);
        return statement.executeUpdateDelete();
    }

    public int update(String table, int conflictAlgorithm, ContentValues values, String whereClause, Object[] whereArgs) {
        if (values == null || values.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
        StringBuilder sql = new StringBuilder(120);
        sql.append("UPDATE ");
        sql.append(CONFLICT_VALUES[conflictAlgorithm]);
        sql.append(table);
        sql.append(" SET ");
        int setValuesSize = values.size();
        int bindArgsSize = whereArgs == null ? setValuesSize : whereArgs.length + setValuesSize;
        Object[] bindArgs = new Object[bindArgsSize];
        int i = 0;
        for (String colName : values.keySet()) {
            sql.append(i > 0 ? "," : "");
            sql.append(colName);
            bindArgs[i] = values.get(colName);
            sql.append("=?");
            i++;
        }
        if (whereArgs != null) {
            for (int i2 = setValuesSize; i2 < bindArgsSize; i2++) {
                bindArgs[i2] = whereArgs[i2 - setValuesSize];
            }
        }
        if (!TextUtils.isEmpty(whereClause)) {
            sql.append(" WHERE ");
            sql.append(whereClause);
        }
        SupportSQLiteStatement stmt = compileStatement(sql.toString());
        SimpleSQLiteQuery.bind(stmt, bindArgs);
        return stmt.executeUpdateDelete();
    }

    public void execSQL(String sql) throws SQLException {
        this.mDelegate.execSQL(sql);
    }

    public void execSQL(String sql, Object[] bindArgs) throws SQLException {
        this.mDelegate.execSQL(sql, bindArgs);
    }

    public boolean isReadOnly() {
        return this.mDelegate.isReadOnly();
    }

    public boolean isOpen() {
        return this.mDelegate.isOpen();
    }

    public boolean needUpgrade(int newVersion) {
        return this.mDelegate.needUpgrade(newVersion);
    }

    public String getPath() {
        return this.mDelegate.getPath();
    }

    public void setLocale(Locale locale) {
        this.mDelegate.setLocale(locale);
    }

    public void setMaxSqlCacheSize(int cacheSize) {
        this.mDelegate.setMaxSqlCacheSize(cacheSize);
    }

    @RequiresApi(api = 16)
    public void setForeignKeyConstraintsEnabled(boolean enable) {
        this.mDelegate.setForeignKeyConstraintsEnabled(enable);
    }

    public boolean enableWriteAheadLogging() {
        return this.mDelegate.enableWriteAheadLogging();
    }

    @RequiresApi(api = 16)
    public void disableWriteAheadLogging() {
        this.mDelegate.disableWriteAheadLogging();
    }

    @RequiresApi(api = 16)
    public boolean isWriteAheadLoggingEnabled() {
        return this.mDelegate.isWriteAheadLoggingEnabled();
    }

    public List<Pair<String, String>> getAttachedDbs() {
        return this.mDelegate.getAttachedDbs();
    }

    public boolean isDatabaseIntegrityOk() {
        return this.mDelegate.isDatabaseIntegrityOk();
    }

    public void close() throws IOException {
        this.mDelegate.close();
    }
}
