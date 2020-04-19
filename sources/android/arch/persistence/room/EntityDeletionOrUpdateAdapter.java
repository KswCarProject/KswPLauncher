package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class EntityDeletionOrUpdateAdapter<T> extends SharedSQLiteStatement {
    /* access modifiers changed from: protected */
    public abstract void bind(SupportSQLiteStatement supportSQLiteStatement, T t);

    /* access modifiers changed from: protected */
    public abstract String createQuery();

    public EntityDeletionOrUpdateAdapter(RoomDatabase database) {
        super(database);
    }

    public final int handle(T entity) {
        SupportSQLiteStatement stmt = acquire();
        try {
            bind(stmt, entity);
            return stmt.executeUpdateDelete();
        } finally {
            release(stmt);
        }
    }

    public final int handleMultiple(Iterable<T> entities) {
        SupportSQLiteStatement stmt = acquire();
        int total = 0;
        try {
            for (T entity : entities) {
                bind(stmt, entity);
                total += stmt.executeUpdateDelete();
            }
            return total;
        } finally {
            release(stmt);
        }
    }

    public final int handleMultiple(T[] entities) {
        SupportSQLiteStatement stmt = acquire();
        int total = 0;
        try {
            for (T entity : entities) {
                bind(stmt, entity);
                total += stmt.executeUpdateDelete();
            }
            return total;
        } finally {
            release(stmt);
        }
    }
}
