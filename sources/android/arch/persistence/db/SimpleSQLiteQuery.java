package android.arch.persistence.db;

import android.support.annotation.Nullable;

public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    @Nullable
    private final Object[] mBindArgs;
    private final String mQuery;

    public SimpleSQLiteQuery(String query, @Nullable Object[] bindArgs) {
        this.mQuery = query;
        this.mBindArgs = bindArgs;
    }

    public SimpleSQLiteQuery(String query) {
        this(query, (Object[]) null);
    }

    public String getSql() {
        return this.mQuery;
    }

    public void bindTo(SupportSQLiteProgram statement) {
        bind(statement, this.mBindArgs);
    }

    public int getArgCount() {
        if (this.mBindArgs == null) {
            return 0;
        }
        return this.mBindArgs.length;
    }

    public static void bind(SupportSQLiteProgram statement, Object[] bindArgs) {
        if (bindArgs != null) {
            int limit = bindArgs.length;
            for (int i = 0; i < limit; i++) {
                bind(statement, i + 1, bindArgs[i]);
            }
        }
    }

    private static void bind(SupportSQLiteProgram statement, int index, Object arg) {
        if (arg == null) {
            statement.bindNull(index);
        } else if (arg instanceof byte[]) {
            statement.bindBlob(index, (byte[]) arg);
        } else if (arg instanceof Float) {
            statement.bindDouble(index, (double) ((Float) arg).floatValue());
        } else if (arg instanceof Double) {
            statement.bindDouble(index, ((Double) arg).doubleValue());
        } else if (arg instanceof Long) {
            statement.bindLong(index, ((Long) arg).longValue());
        } else if (arg instanceof Integer) {
            statement.bindLong(index, (long) ((Integer) arg).intValue());
        } else if (arg instanceof Short) {
            statement.bindLong(index, (long) ((Short) arg).shortValue());
        } else if (arg instanceof Byte) {
            statement.bindLong(index, (long) ((Byte) arg).byteValue());
        } else if (arg instanceof String) {
            statement.bindString(index, (String) arg);
        } else if (arg instanceof Boolean) {
            statement.bindLong(index, ((Boolean) arg).booleanValue() ? 1 : 0);
        } else {
            throw new IllegalArgumentException("Cannot bind " + arg + " at index " + index + " Supported types: null, byte[], float, double, long, int, short, byte," + " string");
        }
    }
}
