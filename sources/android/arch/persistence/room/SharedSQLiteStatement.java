package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteStatement;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class SharedSQLiteStatement {
    private final RoomDatabase mDatabase;
    private final AtomicBoolean mLock = new AtomicBoolean(false);
    private volatile SupportSQLiteStatement mStmt;

    /* access modifiers changed from: protected */
    public abstract String createQuery();

    public SharedSQLiteStatement(RoomDatabase database) {
        this.mDatabase = database;
    }

    /* access modifiers changed from: protected */
    public void assertNotMainThread() {
        this.mDatabase.assertNotMainThread();
    }

    private SupportSQLiteStatement createNewStatement() {
        return this.mDatabase.compileStatement(createQuery());
    }

    private SupportSQLiteStatement getStmt(boolean canUseCached) {
        if (!canUseCached) {
            return createNewStatement();
        }
        if (this.mStmt == null) {
            this.mStmt = createNewStatement();
        }
        return this.mStmt;
    }

    public SupportSQLiteStatement acquire() {
        assertNotMainThread();
        return getStmt(this.mLock.compareAndSet(false, true));
    }

    public void release(SupportSQLiteStatement statement) {
        if (statement == this.mStmt) {
            this.mLock.set(false);
        }
    }
}
