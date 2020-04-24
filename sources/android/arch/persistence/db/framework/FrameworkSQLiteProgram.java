package android.arch.persistence.db.framework;

import android.arch.persistence.db.SupportSQLiteProgram;
import android.database.sqlite.SQLiteProgram;

class FrameworkSQLiteProgram implements SupportSQLiteProgram {
    private final SQLiteProgram mDelegate;

    FrameworkSQLiteProgram(SQLiteProgram delegate) {
        this.mDelegate = delegate;
    }

    public void bindNull(int index) {
        this.mDelegate.bindNull(index);
    }

    public void bindLong(int index, long value) {
        this.mDelegate.bindLong(index, value);
    }

    public void bindDouble(int index, double value) {
        this.mDelegate.bindDouble(index, value);
    }

    public void bindString(int index, String value) {
        this.mDelegate.bindString(index, value);
    }

    public void bindBlob(int index, byte[] value) {
        this.mDelegate.bindBlob(index, value);
    }

    public void clearBindings() {
        this.mDelegate.clearBindings();
    }

    public void close() {
        this.mDelegate.close();
    }
}
