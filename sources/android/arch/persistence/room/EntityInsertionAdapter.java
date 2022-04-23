package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class EntityInsertionAdapter<T> extends SharedSQLiteStatement {
    /* access modifiers changed from: protected */
    public abstract void bind(SupportSQLiteStatement supportSQLiteStatement, T t);

    public EntityInsertionAdapter(RoomDatabase database) {
        super(database);
    }

    public final void insert(T entity) {
        SupportSQLiteStatement stmt = acquire();
        try {
            bind(stmt, entity);
            stmt.executeInsert();
        } finally {
            release(stmt);
        }
    }

    public final void insert(T[] entities) {
        SupportSQLiteStatement stmt = acquire();
        try {
            for (T entity : entities) {
                bind(stmt, entity);
                stmt.executeInsert();
            }
        } finally {
            release(stmt);
        }
    }

    public final void insert(Iterable<T> entities) {
        SupportSQLiteStatement stmt = acquire();
        try {
            for (T entity : entities) {
                bind(stmt, entity);
                stmt.executeInsert();
            }
        } finally {
            release(stmt);
        }
    }

    public final long insertAndReturnId(T entity) {
        SupportSQLiteStatement stmt = acquire();
        try {
            bind(stmt, entity);
            return stmt.executeInsert();
        } finally {
            release(stmt);
        }
    }

    public final long[] insertAndReturnIdsArray(Collection<T> entities) {
        SupportSQLiteStatement stmt = acquire();
        try {
            long[] result = new long[entities.size()];
            int index = 0;
            for (T entity : entities) {
                bind(stmt, entity);
                result[index] = stmt.executeInsert();
                index++;
            }
            return result;
        } finally {
            release(stmt);
        }
    }

    public final long[] insertAndReturnIdsArray(T[] entities) {
        SupportSQLiteStatement stmt = acquire();
        try {
            long[] result = new long[entities.length];
            int index = 0;
            for (T entity : entities) {
                bind(stmt, entity);
                result[index] = stmt.executeInsert();
                index++;
            }
            return result;
        } finally {
            release(stmt);
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(Collection<T> entities) {
        SupportSQLiteStatement stmt = acquire();
        try {
            Long[] result = new Long[entities.size()];
            int index = 0;
            for (T entity : entities) {
                bind(stmt, entity);
                result[index] = Long.valueOf(stmt.executeInsert());
                index++;
            }
            return result;
        } finally {
            release(stmt);
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(T[] entities) {
        SupportSQLiteStatement stmt = acquire();
        try {
            Long[] result = new Long[entities.length];
            int index = 0;
            for (T entity : entities) {
                bind(stmt, entity);
                result[index] = Long.valueOf(stmt.executeInsert());
                index++;
            }
            return result;
        } finally {
            release(stmt);
        }
    }

    public final List<Long> insertAndReturnIdsList(T[] entities) {
        SupportSQLiteStatement stmt = acquire();
        try {
            List<Long> result = new ArrayList<>(entities.length);
            int index = 0;
            for (T entity : entities) {
                bind(stmt, entity);
                result.add(index, Long.valueOf(stmt.executeInsert()));
                index++;
            }
            return result;
        } finally {
            release(stmt);
        }
    }

    public final List<Long> insertAndReturnIdsList(Collection<T> entities) {
        SupportSQLiteStatement stmt = acquire();
        try {
            List<Long> result = new ArrayList<>(entities.size());
            int index = 0;
            for (T entity : entities) {
                bind(stmt, entity);
                result.add(index, Long.valueOf(stmt.executeInsert()));
                index++;
            }
            return result;
        } finally {
            release(stmt);
        }
    }
}
