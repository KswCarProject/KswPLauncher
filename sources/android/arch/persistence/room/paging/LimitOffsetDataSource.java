package android.arch.persistence.room.paging;

import android.arch.paging.PositionalDataSource;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class LimitOffsetDataSource<T> extends PositionalDataSource<T> {
    private final String mCountQuery;
    private final RoomDatabase mDb;
    private final boolean mInTransaction;
    private final String mLimitOffsetQuery;
    private final InvalidationTracker.Observer mObserver;
    private final RoomSQLiteQuery mSourceQuery;

    /* access modifiers changed from: protected */
    public abstract List<T> convertRows(Cursor cursor);

    protected LimitOffsetDataSource(RoomDatabase db, SupportSQLiteQuery query, boolean inTransaction, String... tables) {
        this(db, RoomSQLiteQuery.copyFrom(query), inTransaction, tables);
    }

    protected LimitOffsetDataSource(RoomDatabase db, RoomSQLiteQuery query, boolean inTransaction, String... tables) {
        this.mDb = db;
        this.mSourceQuery = query;
        this.mInTransaction = inTransaction;
        this.mCountQuery = "SELECT COUNT(*) FROM ( " + query.getSql() + " )";
        this.mLimitOffsetQuery = "SELECT * FROM ( " + query.getSql() + " ) LIMIT ? OFFSET ?";
        AnonymousClass1 r0 = new InvalidationTracker.Observer(tables) {
            public void onInvalidated(Set<String> set) {
                LimitOffsetDataSource.this.invalidate();
            }
        };
        this.mObserver = r0;
        db.getInvalidationTracker().addWeakObserver(r0);
    }

    public int countItems() {
        RoomSQLiteQuery sqLiteQuery = RoomSQLiteQuery.acquire(this.mCountQuery, this.mSourceQuery.getArgCount());
        sqLiteQuery.copyArgumentsFrom(this.mSourceQuery);
        Cursor cursor = this.mDb.query(sqLiteQuery);
        try {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            }
            cursor.close();
            sqLiteQuery.release();
            return 0;
        } finally {
            cursor.close();
            sqLiteQuery.release();
        }
    }

    public boolean isInvalid() {
        this.mDb.getInvalidationTracker().refreshVersionsSync();
        return LimitOffsetDataSource.super.isInvalid();
    }

    public void loadInitial(PositionalDataSource.LoadInitialParams params, PositionalDataSource.LoadInitialCallback<T> callback) {
        int totalCount = countItems();
        if (totalCount == 0) {
            callback.onResult(Collections.emptyList(), 0, 0);
            return;
        }
        int firstLoadPosition = computeInitialLoadPosition(params, totalCount);
        int firstLoadSize = computeInitialLoadSize(params, firstLoadPosition, totalCount);
        List<T> list = loadRange(firstLoadPosition, firstLoadSize);
        if (list == null || list.size() != firstLoadSize) {
            invalidate();
        } else {
            callback.onResult(list, firstLoadPosition, totalCount);
        }
    }

    public void loadRange(PositionalDataSource.LoadRangeParams params, PositionalDataSource.LoadRangeCallback<T> callback) {
        List<T> list = loadRange(params.startPosition, params.loadSize);
        if (list != null) {
            callback.onResult(list);
        } else {
            invalidate();
        }
    }

    public List<T> loadRange(int startPosition, int loadCount) {
        RoomSQLiteQuery sqLiteQuery = RoomSQLiteQuery.acquire(this.mLimitOffsetQuery, this.mSourceQuery.getArgCount() + 2);
        sqLiteQuery.copyArgumentsFrom(this.mSourceQuery);
        sqLiteQuery.bindLong(sqLiteQuery.getArgCount() - 1, (long) loadCount);
        sqLiteQuery.bindLong(sqLiteQuery.getArgCount(), (long) startPosition);
        if (this.mInTransaction) {
            this.mDb.beginTransaction();
            Cursor cursor = null;
            try {
                cursor = this.mDb.query(sqLiteQuery);
                List<T> rows = convertRows(cursor);
                this.mDb.setTransactionSuccessful();
                return rows;
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                this.mDb.endTransaction();
                sqLiteQuery.release();
            }
        } else {
            Cursor cursor2 = this.mDb.query(sqLiteQuery);
            try {
                return convertRows(cursor2);
            } finally {
                cursor2.close();
                sqLiteQuery.release();
            }
        }
    }
}
