package android.arch.persistence.p000db.framework;

import android.arch.persistence.p000db.SupportSQLiteStatement;
import android.database.sqlite.SQLiteStatement;

/* renamed from: android.arch.persistence.db.framework.FrameworkSQLiteStatement */
/* loaded from: classes.dex */
class FrameworkSQLiteStatement extends FrameworkSQLiteProgram implements SupportSQLiteStatement {
    private final SQLiteStatement mDelegate;

    FrameworkSQLiteStatement(SQLiteStatement delegate) {
        super(delegate);
        this.mDelegate = delegate;
    }

    @Override // android.arch.persistence.p000db.SupportSQLiteStatement
    public void execute() {
        this.mDelegate.execute();
    }

    @Override // android.arch.persistence.p000db.SupportSQLiteStatement
    public int executeUpdateDelete() {
        return this.mDelegate.executeUpdateDelete();
    }

    @Override // android.arch.persistence.p000db.SupportSQLiteStatement
    public long executeInsert() {
        return this.mDelegate.executeInsert();
    }

    @Override // android.arch.persistence.p000db.SupportSQLiteStatement
    public long simpleQueryForLong() {
        return this.mDelegate.simpleQueryForLong();
    }

    @Override // android.arch.persistence.p000db.SupportSQLiteStatement
    public String simpleQueryForString() {
        return this.mDelegate.simpleQueryForString();
    }
}
