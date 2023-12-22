package android.arch.persistence.room;

import android.arch.persistence.p000db.SupportSQLiteStatement;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public abstract class SharedSQLiteStatement {
    private final RoomDatabase mDatabase;
    private final AtomicBoolean mLock = new AtomicBoolean(false);
    private volatile SupportSQLiteStatement mStmt;

    protected abstract String createQuery();

    public SharedSQLiteStatement(RoomDatabase database) {
        this.mDatabase = database;
    }

    protected void assertNotMainThread() {
        this.mDatabase.assertNotMainThread();
    }

    private SupportSQLiteStatement createNewStatement() {
        String query = createQuery();
        return this.mDatabase.compileStatement(query);
    }

    private SupportSQLiteStatement getStmt(boolean canUseCached) {
        if (canUseCached) {
            if (this.mStmt == null) {
                this.mStmt = createNewStatement();
            }
            SupportSQLiteStatement stmt = this.mStmt;
            return stmt;
        }
        SupportSQLiteStatement stmt2 = createNewStatement();
        return stmt2;
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
